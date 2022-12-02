package com.lx.lib_base.exception

import java.lang.RuntimeException

/**
 * @title：ApiException
 * @projectName Szt
 * @description: <Description>
 * @author linxiao
 * @data Created in 2021/05/06
 */
class ApiException(var code: Int, override var message: String) : RuntimeException()
