package miu.videokabbee.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import miu.videokabbee.dto.ReportDTO;
import miu.videokabbee.service.Report.VideoReportInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
@Validated
public class VideoReportController {
    private  final VideoReportInterface videoReportInterface;


    // admin can only see the reports

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getReports(){

        return  new ResponseEntity<>(videoReportInterface.findAllReport(), HttpStatus.OK);
    }

    // report is  created  by users or admin for a specific problem  they had

    @PostMapping("create")
    public ResponseEntity<?>createVideoReport(@Valid @RequestBody ReportDTO reportDTO ){

        videoReportInterface.addReport(reportDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
