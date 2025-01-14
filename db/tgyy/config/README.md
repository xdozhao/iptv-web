## 天光云影配置说明

### 配置文件

##### 实时更新配置：[active.json](https://raw.githubusercontent.com/xdozhao/iptv-web/refs/heads/main/db/tgyy/config/active.json)

### 配置文件CDN

#### https://cdn.jsdelivr.net

##### 实时更新配置：[active-cdn.json](https://cdn.jsdelivr.net/gh/xdozhao/iptv-web@refs/heads/main/db/tgyy/config/active-cdn.json)

### 配置说明

```json5
{
  // 版本号
  "version": "3.3.7",
  //时间戳
  "syncAt": 1735309277085,
  // 配置名称
  "syncFrom": "天光云影配置",
  //配置描述
  "description": null,
  "configs": {
    /////////////////////////////////////////////////////////////////////////
    ///  应用
    /////////////////////////////////////////////////////////////////////////
    // 开机自启
    "appBootLaunch": false,
    // 画中画
    "appPipEnable": false,
    "appLastLatestVersion": "",
    "appAgreementAgreed": true,
    // 打开直接进入直播 Live(是) Dashboard(否)
    "appStartupScreen": "Dashboard",
    /////////////////////////////////////////////////////////////////////////
    ///  调试
    /////////////////////////////////////////////////////////////////////////
    // 开发者模式
    "debugDeveloperMode": false,
    // FPS显示
    "debugShowFps": false,
    // 显示播放器信息
    "debugShowVideoPlayerMetadata": false,
    // 显示布局网格
    "debugShowLayoutGrids": false,
    /////////////////////////////////////////////////////////////////////////
    ///  直播源
    /////////////////////////////////////////////////////////////////////////
    // 直播源缓存时间
    "iptvSourceCacheTime": 86400000,
    // 当前直播源
    "iptvSourceCurrent": {
      // 名称
      "name": "默认直播源（IPV6）",
      // 链接
      "url": "https://live.fanmingming.cn/tv/m3u/ipv6.m3u",
      // 本地文件
      "isLocal": false,
      // 转换JS
      "transformJs": null
    },
    // 直播源列表
    "iptvSourceList": {
      "value": [
        {
          "name": "默认直播源（IPV6）",
          "url": "https://live.fanmingming.cn/tv/m3u/ipv6.m3u",
          "isLocal": false,
          "transformJs": null
        },
        {
          "name": "电视直播",
          "url": "https://tv.iill.top/m3u/Gather",
          "isLocal": false,
          "transformJs": null
        },
        {
          "name": "网络直播",
          "url": "https://tv.iill.top/m3u/Live",
          "isLocal": false,
          "transformJs": null
        },
        {
          "name": "体育直播",
          "url": "https://tv.iill.top/m3u/Sport",
          "isLocal": false,
          "transformJs": null
        },
        {
          "name": "MyTV直播",
          "url": "https://tv.iill.top/m3u/MyTV",
          "isLocal": false,
          "transformJs": null
        }
      ]
    },
    // 频道分组隐藏
    "iptvChannelGroupHiddenList": [],
    // iptv混合模式
    "iptvHybridMode": "DISABLE",
    // 相似频道合并
    "iptvSimilarChannelMerge": false,
    // 频道图标提供
    "iptvChannelLogoProvider": "https://live.fanmingming.cn/tv/{name|uppercase}.png",
    // 频道图标提供
    "iptvChannelLogoOverride": false,
    // iptv频道收藏夹启用
    "iptvChannelFavoriteEnable": true,
    "iptvChannelFavoriteListVisible": false,
    "iptvChannelFavoriteList": {
      "value": []
    },
    "iptvChannelLastPlay": null,
    "iptvChannelLinePlayableHostList": null,
    "iptvChannelLinePlayableUrlList": null,
    /////////////////////////////////////////////////////////////////////////
    ///  控制
    /////////////////////////////////////////////////////////////////////////
    // 换台反转
    "iptvChannelChangeFlip": false,
    // 数字选台
    "iptvChannelNoSelectEnable": true,
    // 换台列表首尾循环
    "iptvChannelChangeListLoop": false,
    /////////////////////////////////////////////////////////////////////////
    ///  节目单
    /////////////////////////////////////////////////////////////////////////
    // 节目单启用
    "epgEnable": true,
    // 自定义节目单
    "epgSourceCurrent": {
      "name": "默认节目单 fanmingming",
      "url": "https://live.fanmingming.cn/e.xml"
    },
    // 节目单列表
    "epgSourceList": {
      "value": []
    },
    // 刷新时间阈值
    "epgRefreshTimeThreshold": 2,
    // 跟随直播源
    "epgSourceFollowIptv": false,
    //
    "epgChannelReserveList": {
      "value": []
    },
    /////////////////////////////////////////////////////////////////////////
    ///  界面
    /////////////////////////////////////////////////////////////////////////
    // 节目进度
    "uiShowEpgProgrammeProgress": true,
    // 常驻节目进度
    "uiShowEpgProgrammePermanentProgress": false,
    // 台标显示
    "uiShowChannelLogo": true,
    // 频道预览
    "uiShowChannelPreview": false,
    // 经典选台界面 三段式结构
    "uiUseClassicPanelScreen": false,
    // 界面整体缩放比例
    "uiDensityScaleRatio": 0,
    // 界面字体缩放比例
    "uiFontScaleRatio": 1,
    // 时间显示
    "uiTimeShowMode": "HIDDEN",
    // 焦点优化
    "uiFocusOptimize": true,
    // 超时自动关闭界面 ms
    "uiScreenAutoCloseDelay": 15000,
    /////////////////////////////////////////////////////////////////////////
    ///  更新
    /////////////////////////////////////////////////////////////////////////
    // 更新强提醒
    "updateForceRemind": false,
    // 更新通道
    "updateChannel": "stable",
    /////////////////////////////////////////////////////////////////////////
    ///  播放器
    /////////////////////////////////////////////////////////////////////////
    // 内核
    "videoPlayerCore": "MEDIA3",
    // 渲染方式
    "videoPlayerRenderMode": "SURFACE_VIEW",
    // 自定义ua
    "videoPlayerUserAgent": "okhttp",
    // 自定义headers
    "videoPlayerHeaders": "",
    // 加载超时
    "videoPlayerLoadTimeout": 15000,
    // 全局显示模式
    "videoPlayerDisplayMode": "ORIGINAL",
    // 强制音频软解
    "videoPlayerForceAudioSoftDecode": false,
    // 停止上一媒体项
    "videoPlayerStopPreviousMediaItem": false,
    // 跳过多帧渲染
    "videoPlayerSkipMultipleFramesOnSameVSync": true,
    /////////////////////////////////////////////////////////////////////////
    ///  主题
    /////////////////////////////////////////////////////////////////////////
    // 主题
    "themeAppCurrent": {
      "name": "心想事橙",
      "background": "iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAAXNSR0IArs4c6QAAAHRJREFUGFcBaQCW/wGScBzA/v73/vr9+QD19gEB8voECQGQbyrK+/0A/PH1+wbw9vcW9/z8DwGNbkS89/kNAOnw+hnv9eYf+//wCQGEaYWZ8ff+D+bs5Cru9Nwi+PzqCgGAZbGD8vXuCuXv0inn79Qv+P3vFHi9Qmo/Gx+4AAAAAElFTkSuQmCC",
      "texture": "https://qqpatch.gtimg.cn/template_theme/texture/1004.svg",
      "textureAlpha": 0.2
    },
    /////////////////////////////////////////////////////////////////////////
    ///  云同步
    /////////////////////////////////////////////////////////////////////////
    // 自动拉取
    "cloudSyncAutoPull": null,
    "cloudSyncProvider": null,
    "cloudSyncGithubGistId": null,
    "cloudSyncGithubGistToken": null,
    "cloudSyncGiteeGistId": null,
    "cloudSyncGiteeGistToken": null,
    "cloudSyncNetworkUrl": null,
    "cloudSyncLocalFilePath": null,
    "cloudSyncWebDavUrl": null,
    "cloudSyncWebDavUsername": null,
    "cloudSyncWebDavPassword": null,
    "feiyangAllInOneFilePath": ""
  },
  "extraLocalIptvSourceList": {},
  "extraChannelNameAlias": ""
}

```

