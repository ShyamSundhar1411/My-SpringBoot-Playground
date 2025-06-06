// TrackDto.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.axionlabs.meldora.dto.music;
import java.util.List;
import lombok.Data;

@Data
public class TrackDto {
    private String next;
    private long total;
    private long offset;
    private long limit;
    private String href;
    private List<ItemDto> items;
}