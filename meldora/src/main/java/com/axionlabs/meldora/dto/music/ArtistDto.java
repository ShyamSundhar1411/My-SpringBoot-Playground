// ArtistDto.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.axionlabs.meldora.dto.music;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ArtistDto {
    private String name;
    private String href;
    private String id;
    private String type;
    private ExternalUrlsDto externalUrls;
    private String uri;


}

