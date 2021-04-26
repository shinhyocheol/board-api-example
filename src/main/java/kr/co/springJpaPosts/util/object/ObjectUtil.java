package kr.co.springJpaPosts.util.object;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ObjectUtil {

    private static ModelMapper modelMapper;

    /**
     * @Method 설명 : entity의 데이터들을 dto로 modelmapper 라이브러리를 통해 할당함.
     * @param <T> Dto 타입의 제네릭 변수
     * @param <V> Entity 타입의 제네릭 변수
     * @return
     */
    public static <T, V> T toDto(V entity, Class<T> dto) {

        return dto.cast(modelMapper.map(entity, dto));
    }

}
