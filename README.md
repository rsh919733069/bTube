# bTube

> 基于 bili_tube 开源项目的优化复刻版本

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue" alt="Language">
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-orange" alt="UI">
  <img src="https://img.shields.io/badge/Status-In%20Development-yellow" alt="Status">
</p>

## 📖 项目简介

bTube 是基于开源项目 [bili_tube](../bili_tube/) 进行复刻和优化的 Android 应用，旨在学习 B站 和 YouTube 客户端 UI 设计，并在原项目基础上进行功能完善和性能优化。

### ✨ 主要特性

- 🎨 **现代化UI**: 基于 Jetpack Compose + Material 3 设计
- 📱 **自适应布局**: 支持手机、平板、折叠屏等多种设备
- 🎬 **强大播放器**: 基于 Media3 的高性能视频播放器
- 🔍 **智能搜索**: 快速搜索视频、用户、番剧等内容
- 📥 **离线下载**: 支持视频下载，离线观看
- 🎭 **多种登录方式**: 支持二维码、短信、密码登录
- 🌙 **深色模式**: 完整的深色模式支持
- ⚡ **高性能**: 优化的图片加载和列表滚动

## 🏗️ 技术栈

### 核心框架
- **UI框架**: Jetpack Compose + Material 3
- **架构模式**: MVVM + Repository Pattern
- **依赖注入**: Koin 4.1.1
- **网络请求**: Ktor Client 3.3.1
- **导航**: Navigation Compose 2.9.5

### 数据层
- **数据库**: Room 2.8.2
- **持久化**: DataStore 1.1.7 + SharedPreferences
- **分页**: Paging 3 (3.3.6)
- **序列化**: Kotlin Serialization

### 多媒体
- **视频播放**: Media3 ExoPlayer 1.8.0
- **图片加载**: Coil 3.3.0
- **动画**: Lottie 6.6.10
- **多媒体处理**: FFmpeg

### 其他
- **二维码**: ZXing 3.5.3
- **加密**: Conscrypt 2.5.3
- **时间**: kotlinx-datetime

## 📂 项目结构

```
bTube/
├── app/                          # 主应用模块
├── bili_sdk/                     # B站SDK（API + 数据模型）[待开发]
├── common_ui_core/               # 通用UI组件 [待开发]
├── feature_annotations/          # 特性注解 [待开发]
├── docs/                         # 项目文档
│   ├── 复刻计划.md              # 详细的开发计划
│   ├── 开发进度.md              # 进度跟踪
│   └── 技术架构.md              # 技术架构文档
├── gradle/                       # Gradle配置
├── build.gradle.kts              # 项目级构建配置
├── settings.gradle.kts           # 项目设置
└── README.md                     # 项目说明
```

## 🚀 开发计划

项目分为 **14个阶段** 进行开发，当前处于：

### ✅ 阶段 0: 项目初始化（已完成）
- 深入分析源项目架构和功能
- 制定详细的复刻计划
- 建立开发文档体系

### ⏳ 阶段 1: 基础架构搭建（准备开始）
- 配置项目依赖
- 创建基础模块
- 初始化应用核心
- 建立核心工具类

### 📋 后续阶段
- 阶段 2: SDK 模块开发
- 阶段 3: 数据层开发
- 阶段 4: UI 基础组件
- 阶段 5: 启动与登录功能
- 阶段 6: 主页功能
- 阶段 7: 视频播放器
- 阶段 8: 搜索功能
- 阶段 9: 历史与播放列表
- 阶段 10: 下载功能
- 阶段 11: 设置功能
- 阶段 12: 其他功能
- 阶段 13: 测试与优化
- 阶段 14: 打包发布

详细计划请查看 [复刻计划文档](./docs/复刻计划.md)

## 📊 开发进度

**总体进度**: 7.14% (1/14 阶段完成)

| 模块 | 状态 | 完成度 |
|------|------|--------|
| 项目初始化 | ✅ 已完成 | 100% |
| 基础架构 | ⏳ 待开始 | 0% |
| SDK开发 | ⏳ 待开始 | 0% |
| 数据层 | ⏳ 待开始 | 0% |
| UI组件 | ⏳ 待开始 | 0% |
| 登录功能 | ⏳ 待开始 | 0% |
| 主页功能 | ⏳ 待开始 | 0% |
| 播放器 | ⏳ 待开始 | 0% |
| 搜索功能 | ⏳ 待开始 | 0% |
| 历史记录 | ⏳ 待开始 | 0% |
| 下载功能 | ⏳ 待开始 | 0% |
| 设置功能 | ⏳ 待开始 | 0% |

实时进度请查看 [开发进度文档](./docs/开发进度.md)

## 🎯 改进计划

相比原项目，bTube 将在以下方面进行优化：

### 1. 架构优化
- ✅ 使用最新版本依赖库
- ✅ 优化模块间依赖关系
- ✅ 实现更清晰的分层架构
- ✅ 添加统一的异常处理

### 2. 性能优化
- ✅ 优化列表滚动性能
- ✅ 实现智能图片加载
- ✅ 优化视频播放性能
- ✅ 减少内存占用
- ✅ 优化启动速度

### 3. 用户体验优化
- ✅ 改进UI设计细节
- ✅ 添加更流畅的动画
- ✅ 优化交互反馈
- ✅ 支持平板自适应布局
- ✅ 实现深色模式优化

### 4. 功能完善
- ✅ 添加更多播放器功能
- ✅ 实现更完整的下载功能
- ✅ 添加更多设置选项
- ✅ 实现数据同步功能

### 5. 代码质量
- ✅ 添加完整的代码注释
- ✅ 编写单元测试
- ✅ 统一代码风格
- ✅ 优化命名规范

## 📚 文档

- [复刻计划](./docs/复刻计划.md) - 详细的14阶段开发计划
- [开发进度](./docs/开发进度.md) - 实时开发进度跟踪
- [技术架构](./docs/技术架构.md) - 技术架构详解

## 🛠️ 开发环境

### 系统要求
- Android Studio Ladybug | 2024.2.1 或更高版本
- JDK 11 或更高版本
- Android SDK 36
- Kotlin 2.2.20

### 构建项目
```bash
# 克隆项目
git clone [项目地址]

# 进入项目目录
cd bTube

# 使用 Gradle 构建
./gradlew build

# 运行应用
./gradlew installDebug
```

## ⚠️ 声明

1. 本项目遵守 CC-BY-NC 4.0 协议，禁止一切商业使用
2. **仅用于学习和测试，请勿滥用**
3. 利用本项目提供的接口、文档等造成不良影响及后果与本人无关
4. 由于本项目的特殊性，可能随时停止开发或删档
5. 本项目为开源项目，不接受任何形式的催单和索取行为
6. 上传任何信息时请注意脱敏，删去账户密码、敏感 cookies 等信息

## 📄 许可证

本项目基于 [CC-BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) 协议开源

## 🙏 致谢

- 感谢 [bili_tube](../bili_tube/) 原项目作者
- 感谢 [bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect) 提供的 API 文档
- 感谢所有为本项目做出贡献的开发者

## 📧 联系方式

如有问题或建议，欢迎提交 Issue 或 Pull Request

---

**开发状态**: 🚧 积极开发中  
**最后更新**: 2026-02-04  
**项目版本**: v0.1.0-alpha
