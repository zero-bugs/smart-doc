package com.power.doc.builder;

import com.power.doc.model.ApiAllData;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.ApiDoc;
import com.thoughtworks.qdox.JavaProjectBuilder;

/**
 * @since 1.7.9
 * @author yu 2019/12/7.
 */
public class ApiDataBuilder {

    /**
     * Get list of ApiDoc
     *
     * @param config ApiConfig
     * @return List of ApiDoc
     */
    public static ApiAllData getApiData(ApiConfig config) {
        DocBuilderTemplate builderTemplate = new DocBuilderTemplate();
        builderTemplate.checkAndInitForGetApiData(config);
        JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder();
        builderTemplate.getApiData(config,javaProjectBuilder);
        return builderTemplate.getApiData(config,javaProjectBuilder);
    }

    /**
     * Get single api data
     *
     * @param config         ApiConfig
     * @param controllerName controller name
     * @return ApiDoc
     */
    public static ApiDoc getApiData(ApiConfig config, String controllerName) {
        DocBuilderTemplate builderTemplate = new DocBuilderTemplate();
        builderTemplate.checkAndInitForGetApiData(config);
        config.setMd5EncryptedHtmlName(true);
        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        SourceBuilder sourceBuilder = new SourceBuilder(config,projectBuilder);
        return sourceBuilder.getSingleControllerApiData(controllerName);
    }
}
