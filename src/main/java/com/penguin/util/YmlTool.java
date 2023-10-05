package com.penguin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.yaml.snakeyaml.LoaderOptions;

import java.io.File;
import java.io.IOException;

public class YmlTool {
    public static void updateYml(File file, Object info) {
        try {
            ObjectMapper mapper = createMapper();
            mapper.writeValue(file, info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getYml(File file, Class<T> class_) {
        try {
            ObjectMapper mapper = createMapper();
            return mapper.readValue(file, class_);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper createMapper() {
        // 限制大小
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setCodePointLimit(104857600); // 100MB
        YAMLFactory yamlFactory = YAMLFactory.builder()
                .loaderOptions(loaderOptions) // 变更限制器
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER) // 关闭头部信息
                .build();

        return new ObjectMapper(yamlFactory);
    }
}