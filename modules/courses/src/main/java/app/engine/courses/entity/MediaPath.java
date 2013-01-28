package app.engine.courses.entity;

import app.engine.core.EntityDeclaration;
import app.engine.core.ParentEntity;
import app.engine.core.Property;
import app.engine.core.Transient;

/**
 * <p>媒体文件路径实体类</p>
 * User: MichaelLee
 * Date: 13-1-14
 * Time: 下午2:51
 */
public class MediaPath extends EntityDeclaration {

    private String path;// 路径
    private Integer mediaType;// 媒体类型 0 视频 1 音频 2 讲义 3CC地址
    private Video video;// 视频实体
    private Long videoId;// 视频外键
    private Integer duration;//视频片长
    //媒体播放时长(分)
    private Integer mediaTime;
    //播放时长(秒)
    private Integer mediaSecond;
    //缩略图地址
    private String thumbnail;

    /**
     * 无参构造
     */
    public MediaPath() {
    }

    /**
     * 构造函数
     *
     * @param video 视频对象引用
     */
    public MediaPath(Video video) {
        this.video = video;
    }

    /**
     * 获取媒体文件存放路径
     *
     * @return
     */
    @Property(chineseName = "路径")
    public String getPath() {
        return path;
    }

    /**
     * 设置媒体文件存放路径
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取媒体文件类型
     *
     * @return
     */
    @Property(chineseName = "媒体类型")
    public Integer getMediaType() {
        return mediaType;
    }

    /**
     * 设置媒体文件类型
     *
     * @param mediaType
     */
    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 获取视频文件对象
     *
     * @return
     */
    @Property(chineseName = "视频")
    @Transient
    public Video getVideo() {
        return video;
    }

    /**
     * 设置视频文件对象
     *
     * @param video
     */
    public void setVideo(Video video) {
        this.video = video;
    }

    /**
     * 设置视频ID 数据库操作便捷属性
     *
     * @param videoId
     */
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取视频ID 数据库操作便捷属性
     *
     * @return
     */
    @Property(chineseName = "视频ID")
    @ParentEntity(Video.class)
    public Long getVideoId() {
        return videoId;
    }

    /**
     * @return 获取媒体时长（秒）
     */
    @Property(chineseName = "时长")
    public Integer getDuration() {
        if (duration == null || duration == 0) {
            return 0;
        }
        return duration;
    }

    /**
     * 设置影片时长（秒）
     *
     * @param duration 片长
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    private void min_second() {
        mediaTime = getDuration() / 60;
        mediaSecond = getDuration() - mediaTime * 60;
    }

    /**
     * @return 获取媒体时长（分）
     */
    @Property(chineseName = "分")
    @Transient
    public Integer getMediaTime() {
        if (mediaTime == null || mediaTime == 0) {
            min_second();
        }
        return mediaTime;
    }

    /**
     * @return 获取媒体时长（秒）
     */
    @Property(chineseName = "秒")
    @Transient
    public Integer getMediaSecond() {
        if (mediaSecond == null || mediaSecond == 0) {
            min_second();
        }
        return mediaSecond;
    }

    /**
     * @return 获取缩略图地址
     */
    @Property(chineseName = "缩略图地址")
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置缩略图地址
     *
     * @param thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "MediaPath [path=" + path + ", mediaType=" + mediaType
                + ", video=" + video + ", getId()=" + getId() + "]";
    }

}