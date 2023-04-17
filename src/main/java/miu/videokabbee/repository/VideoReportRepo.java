package miu.videokabbee.repository;

import miu.videokabbee.domain.VideoReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  VideoReportRepo extends MongoRepository<VideoReport, Long > {
}
