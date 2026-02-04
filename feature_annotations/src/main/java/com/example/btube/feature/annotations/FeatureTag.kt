package com.example.btube.feature.annotations

/**
 * 功能模块标记注解
 * 用于标识代码所属的模块、层级和功能
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE
)
@Retention(AnnotationRetention.SOURCE)
annotation class FeatureTag(
    /** 模块名，例如 bili_sdk、auth、download、player */
    val module: String,

    /** 简要说明当前类或方法的作用 */
    val desc: String = "",

    /** 代码层级，例如: "data", "domain", "ui", "network" 等 */
    val layer: String = "",

    /** 所属功能或接口用途 */
    val feature: String = "",

    /** 负责人，可选 */
    val owner: String = "",

    /** 当前状态: prototype / beta / stable / deprecated */
    val stage: String = "stable"
)
