package academy.devdojo.mapper;

import academy.devdojo.domain.Producer;
import academy.devdojo.response.ProducerGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mapper(imports = {LocalDateTime.class, ThreadLocalRandom.class})
public interface ProducerMapper {

    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createadAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(ThreadLocalRandom.current().nextLong(100_000))")
    <ProducerPutRequest>
    Producer toProducer(ProducerPutRequest postRequest);

    ProducerGetResponse toProducerGetResponse(Producer producer);

    List<ProducerGetResponse> toProducerGetRresponseList(List<Producer> producers);
}
