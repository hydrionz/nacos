/*
 *
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.alibaba.nacos.client.monitor.naming;

import com.alibaba.nacos.client.monitor.TraceMonitor;
import com.alibaba.nacos.common.constant.NacosSemanticAttributes;
import com.alibaba.nacos.common.utils.VersionUtils;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.SpanKind;

/**
 * Naming traces management.
 *
 * <p>For the trace about Nacos server request to client. See
 * {@link  com.alibaba.nacos.client.monitor.TraceDynamicProxy} method: <b>getServerRequestHandlerTraceProxy()</b>.
 *
 * @author <a href="https://github.com/FAWC438">FAWC438</a>
 */
public class NamingTrace {
    
    private static final String NACOS_CLIENT_NAMING_BASE_SPAN = "Nacos.client.naming";
    
    private static final String NACOS_CLIENT_NAMING_RPC_SPAN = NACOS_CLIENT_NAMING_BASE_SPAN + ".rpc";
    
    private static final String NACOS_CLIENT_NAMING_HTTP_SPAN = NACOS_CLIENT_NAMING_BASE_SPAN + ".http";
    
    private static final String NACOS_CLIENT_NAMING_SERVICE_SPAN = NACOS_CLIENT_NAMING_BASE_SPAN + ".service";
    
    private static final String NACOS_CLIENT_NAMING_WORKER_SPAN = NACOS_CLIENT_NAMING_BASE_SPAN + ".worker";
    
    public static SpanBuilder getClientNamingRpcSpanBuilder(String rpcType) {
        String spanName = NACOS_CLIENT_NAMING_RPC_SPAN + " / " + rpcType.toUpperCase();
        return TraceMonitor.getTracer().spanBuilder(spanName).setSpanKind(SpanKind.CLIENT)
                .setAttribute(NacosSemanticAttributes.CLIENT_VERSION, VersionUtils.getFullClientVersion());
    }
    
    public static SpanBuilder getClientNamingHttpSpanBuilder(String method) {
        String spanName = NACOS_CLIENT_NAMING_HTTP_SPAN + " / " + method.toUpperCase();
        return TraceMonitor.getTracer().spanBuilder(spanName).setSpanKind(SpanKind.CLIENT)
                .setAttribute(NacosSemanticAttributes.CLIENT_VERSION, VersionUtils.getFullClientVersion());
    }
    
    public static SpanBuilder getClientNamingServiceSpanBuilder(String spanNameExtension) {
        String spanName = NACOS_CLIENT_NAMING_SERVICE_SPAN + " / " + spanNameExtension;
        return TraceMonitor.getTracer().spanBuilder(spanName)
                .setAttribute(NacosSemanticAttributes.CLIENT_VERSION, VersionUtils.getFullClientVersion());
    }
    
    public static SpanBuilder getClientNamingWorkerSpanBuilder(String spanNameExtension) {
        String spanName = NACOS_CLIENT_NAMING_WORKER_SPAN + " / " + spanNameExtension;
        return TraceMonitor.getTracer().spanBuilder(spanName)
                .setAttribute(NacosSemanticAttributes.CLIENT_VERSION, VersionUtils.getFullClientVersion());
    }
}
