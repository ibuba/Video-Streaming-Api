package miu.videokabbee.service.Report;

import lombok.RequiredArgsConstructor;
import miu.videokabbee.dto.ReportDTO;
import miu.videokabbee.domain.VideoReport;
import miu.videokabbee.repository.VideoReportRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoReportService implements VideoReportInterface{

    private VideoReportRepo videoReportRepo;
    @Override
    public void addReport(ReportDTO videoReport ) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String username= authentication.getName();
         var x = VideoReport.builder()
                .videoTitle(videoReport.getVideoTitle())
                .id(videoReport.getId())
                .report(videoReport.getReport())
                .customerEmail(username)
                .build();
         videoReportRepo.save(x);
    }

    @Override
    public Iterable<VideoReport> findAllReport() {
      return   videoReportRepo.findAll(Pageable.ofSize(15));
    }

}
