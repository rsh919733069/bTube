# 变更日志

所有重要的项目变更都会记录在此文件中。

---

## [0.3.0-alpha] - 2026-02-04

### ✨ 新增 - 阶段3: 数据层开发

- **Room 数据库**
  - BTubeDB - 4 个实体表
  - SearchHistory, DownloadTask, BiliVideoUrl, BiliAudioUrl

- **DAO 层**
  - SearchHistoryDao - Paging 支持
  - DownloadTaskDao
  - BiliSharedSourceDao

- **Repository 层**
  - SearchHistoryRepository
  - BiliLoginRepository

- **Koin 集成**
  - RoomModule 完整配置
  - DataModule 添加 Repository

---

## [0.2.0-alpha] - 2026-02-04

### ✨ 新增 - 阶段2: SDK 模块开发

- **数据模型层**
  - model_v2 完整数据模型（common, video, user, login, captcha, history）
  - model 基础模型（BiliWbi, BiliQRCode, BiliUserProfile, BiliResponse）

- **WBI 签名系统**
  - WbiParams - WBI参数与 enc 签名算法
  - GetWbi - 从网络获取 WBI 密钥
  - Extensions - MD5、QueryString 工具

- **API 层**
  - AuthApi / AuthApiImpl - 二维码、短信登录、验证码
  - UserApi / UserApiImpl - 用户资料、SPI、投稿视频
  - Apis.kt - 完整的 B站 API URL 常量

- **应用集成**
  - BTubeApp 完整实现 WBI 初始化（缓存优先）
  - SdkModule Koin 依赖注入

---

## [0.1.0-alpha] - 2026-02-04

### ✨ 新增

#### 阶段1: 基础架构搭建
- **项目配置**
  - 创建完整的依赖版本目录（libs.versions.toml）
  - 配置Kotlin序列化、KSP、Room等插件
  - 添加所有必需的依赖库（Compose、Ktor、Koin、Media3等）

- **模块结构**
  - 创建 `bili_sdk` 模块 - B站SDK（API和数据模型）
  - 创建 `common_ui_core` 模块 - 通用UI组件库
  - 创建 `feature_annotations` 模块 - 功能标记注解

- **应用核心**
  - 实现 `BTubeApp` 应用类
    - Koin依赖注入初始化
    - Conscrypt安全提供者配置
    - Coil ImageLoader配置
  - 更新 MainActivity 为欢迎屏幕
  - 配置完整的 AndroidManifest.xml

- **核心工具类**
  - `Constants.kt` - 全局常量定义
  - `HttpClientFactory.kt` - HTTP客户端工厂
  - `DataStoreExt.kt` - DataStore扩展
  - `PreferencesUtil.kt` - SharedPreferences工具
  - `ContextExt.kt` - Context扩展函数集合
  - `EventBus.kt` - 应用级事件总线
  - `ShareTarget.kt` - 分享目标模型

- **依赖注入**
  - `AppModule.kt` - 应用级依赖
  - `DataModule.kt` - 数据层依赖（骨架）
  - `RoomModule.kt` - 数据库依赖（骨架）
  - `ViewModelModule.kt` - ViewModel依赖（骨架）

#### 阶段0: 项目初始化
- **文档体系**
  - 复刻计划文档（14个开发阶段详细规划）
  - 开发进度跟踪文档
  - 技术架构文档
  - 文档导航中心
  - 项目README

### 🔧 改进
- 使用最新稳定版本的依赖库
- 代码添加详细的中文注释
- 统一包名为 `com.example.btube.*`
- 配置Android 13+新权限模型

### 🐛 修复
- 修复 `feature_annotations` 模块的 JVM 目标兼容性问题
  - 问题1: Kotlin 和 Java 编译目标不一致（21 vs 11）
  - 问题2: 系统未安装 JVM 11，无法使用 jvmToolchain
  - 问题3: kotlinOptions 已废弃，需要迁移到 compilerOptions
  - 最终方案: 使用 `kotlin { compilerOptions { jvmTarget.set(JvmTarget.JVM_11) } }`
  - 优势: 使用最新的 Kotlin Gradle 插件 DSL，不依赖系统特定 JVM 版本

- 修复 `PreferencesUtil` 的 inline 函数可见性问题
  - 问题: Public inline 函数无法访问 private 成员，也无法委托给其他 inline 函数
  - 尝试1: 改为 `internal val context` - 失败
  - 最终方案: 直接在类内实现 SharedPreferences 逻辑，不委托给扩展函数
  - 结果: 保留了 inline 和 reified 的性能优化，context 保持 private，编译成功

### 📝 文档
- 创建了完整的项目文档体系
- 添加了详细的代码注释（注释率约30%）
- 编写了14个阶段的开发计划

### 🚧 待完成
- SDK模块的API接口实现
- WBI签名系统实现
- 数据模型定义
- 数据层Repository实现
- UI界面开发

---

## 项目初始化 - 2026-02-04

### ✨ 新增
- 创建 bTube 项目基础结构
- 分析 bili_tube 源项目
- 制定14阶段复刻计划

---

**版本格式**: [版本号] - 日期  
**版本号说明**: 采用语义化版本号 (主版本.次版本.修订号-标签)
