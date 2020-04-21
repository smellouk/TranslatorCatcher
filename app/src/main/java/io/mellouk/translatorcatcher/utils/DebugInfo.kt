package io.mellouk.translatorcatcher.utils

import okhttp3.Interceptor

class DebugInfo(val isDebug: Boolean, val debugInterceptors: List<Interceptor>)