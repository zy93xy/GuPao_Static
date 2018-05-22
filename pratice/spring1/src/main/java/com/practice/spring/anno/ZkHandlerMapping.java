package com.practice.spring.anno;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */
public interface ZkHandlerMapping {


    String PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE = ZkHandlerMapping.class.getName() + ".pathWithinZkHandlerMapping";


    String BEST_MATCHING_PATTERN_ATTRIBUTE = ZkHandlerMapping.class.getName() + ".bestMatchingPattern";


    String INTROSPECT_TYPE_LEVEL_MAPPING = ZkHandlerMapping.class.getName() + ".introspectTypeLevelMapping";


    String URI_TEMPLATE_VARIABLES_ATTRIBUTE = ZkHandlerMapping.class.getName() + ".uriTemplateVariables";

    String MATRIX_VARIABLES_ATTRIBUTE = ZkHandlerMapping.class.getName() + ".matrixVariables";


    String PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE = ZkHandlerMapping.class.getName() + ".producibleMediaTypes";

/*
    @Nullable
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;*/
}
