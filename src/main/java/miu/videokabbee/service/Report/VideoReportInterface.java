package miu.videokabbee.service.Report;

import miu.videokabbee.dto.ReportDTO;
import miu.videokabbee.domain.VideoReport;


public interface VideoReportInterface {
    void addReport(ReportDTO videoReport);
    Iterable<VideoReport> findAllReport();

}
