package com.insurance.insurancemanagementsystem.claim.service.serviceImplementation;

import com.insurance.insurancemanagementsystem.claim.dto.ClaimRequestDTO;
import com.insurance.insurancemanagementsystem.claim.dto.ClaimResponseDTO;
import com.insurance.insurancemanagementsystem.claim.entity.Claim;
import com.insurance.insurancemanagementsystem.claim.entity.ClaimAssignmentHistory;
import com.insurance.insurancemanagementsystem.claim.entity.ClaimMedia;
import com.insurance.insurancemanagementsystem.claim.repository.ClaimAssignmentHistoryRepository;
import com.insurance.insurancemanagementsystem.claim.repository.ClaimRepository;
import com.insurance.insurancemanagementsystem.claim.repository.MediaRepository;
import com.insurance.insurancemanagementsystem.claim.service.ClaimServiceInterface;
import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.common.enums.ClaimStatus;
import com.insurance.insurancemanagementsystem.common.enums.ClaimType;
import com.insurance.insurancemanagementsystem.common.enums.MediaType;
import com.insurance.insurancemanagementsystem.common.exception.ResourceNotFoundException;
import com.insurance.insurancemanagementsystem.customer.entity.Customer;
import com.insurance.insurancemanagementsystem.customer.repository.CustomerRepository;
import com.insurance.insurancemanagementsystem.employee.repository.EmployeeRepository;
import com.insurance.insurancemanagementsystem.insurance.entity.Insurance;
import com.insurance.insurancemanagementsystem.insurance.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClaimService implements ClaimServiceInterface {
    private final InsuranceRepository insuranceRepository;
    private final EmployeeRepository employeeRepository;
    private final ClaimRepository claimRepository;
    private final MediaRepository mediaRepository;
    private final ClaimAssignmentHistoryRepository historyRepository;


    @Override
    public ResponseEntity<List<ClaimResponseDTO>> viewClaimCustomer(Long customer_Id) {

        List<Insurance> insurance1 = insuranceRepository.findByCustomerCustomerId(customer_Id);

        ArrayList<ClaimResponseDTO> listOfInsurance = new ArrayList<>();

        for (Insurance insurance : insurance1) {

            ClaimResponseDTO dto = new ClaimResponseDTO();
            dto.setId(insurance.getId());
            dto.setName(insurance.getVehicle().getCarDetails().getModel().getName());
            dto.setPolicyStatus(insurance.getPolicy().getPolicyStatus());
            dto.setIdvValue(insurance.getVehicle().getIdvValue());
            dto.setRegistrationNumber(insurance.getVehicle().getRegistrationNumber());

            listOfInsurance.add(dto);
        }

        return ResponseEntity.ok(listOfInsurance);
    }

    @Override
    @Transactional
    public ResponseEntity<String> createClaim(ClaimRequestDTO dto) throws IOException {


        Claim claim = new Claim();



        claim.setClaimType(dto.getClaimType());
        claim.setInsurance(insuranceRepository.findById(dto.getInsurance_id()).orElseThrow(()-> new ResourceNotFoundException("Insurance not found...!")));
        claim.setAccidentDateTime(dto.getDateTime());
        claim.setAccidentLocation(dto.getLocation());
        claim.setDescription(dto.getDescription());


        claim.setClaimStatus(ClaimStatus.SUBMITTED);
        claim.setCreatedAt(LocalDateTime.now());
        ClaimType claimSpecialization = dto.getClaimType();
        ClaimSpecialization specialization =
                ClaimSpecialization.valueOf(dto.getClaimType().name());
//        claim.setAssignedEmployee(employeeRepository.findBySpecialization(specialization).stream().findAny().orElseThrow(()-> new ResourceNotFoundException("Employee not exist...!")));
       claimRepository.save(claim);

        // set multimedia file
        saveFiles(dto.getFiles(),claim);

       // store claim history
        ClaimAssignmentHistory history = new ClaimAssignmentHistory();
        history.setClaim(claim);
        history.setEmployee(claim.getAssignedEmployee());
        history.setAssignedAt(LocalDateTime.now());
        historyRepository.save(history);

        return ResponseEntity.ok( "Claim submitted successfully ");
    }

    private void saveFiles(MultipartFile[] files,Claim claim) throws IOException {
//
//        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {

            MediaType mediaType = detectMediaType(file);

            String url = store(file, claim.getId(), mediaType);

            ClaimMedia media = new ClaimMedia();
            media.setClaim(claim);
            media.setMediaType(mediaType);
            media.setMediaUrl(url);

            mediaRepository.save(media);
//            urls.add(url);
        }

//        return urls;

    }

        public  String store(MultipartFile file, Long claimId, MediaType type)
                throws IOException {

            String fileName =
                    UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path folder = Paths.get(
                    "uploads/claims/" + claimId + "/" + type.name().toLowerCase()
            );

            Files.createDirectories(folder);

            Path filePath = folder.resolve(fileName);
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return "http://localhost:8080/uploads/claims/"
                    + claimId + "/" + type.name().toLowerCase()
                    + "/" + fileName;
        }

    private MediaType detectMediaType(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException("Invalid file");
        }

        if (contentType.startsWith("image/")) {
            return MediaType.PHOTO;
        }

        if (contentType.startsWith("video/")) {
            return MediaType.VIDEO;
        }

        throw new IllegalArgumentException("Only photo and video allowed");
    }



}
