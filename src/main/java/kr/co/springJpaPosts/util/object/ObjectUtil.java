package kr.co.springJpaPosts.util.object;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectUtil {

    private ModelMapper modelMapper;

    public ObjectUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * @Method 설명 : entity의 데이터들을 dto로 modelmapper 라이브러리를 통해 같은 네임을 가진 객체에게 값을 매핑시킨다.
     * @param <T> Dto 타입의 제네릭 변수
     * @param <V> Entity 타입의 제네릭 변수
     * @return
     */
    public <T, V> T toDto(V entity, Class<T> dto) {
        return dto.cast(modelMapper.map(entity, dto));
    }

}
