package com.munsang.musicking.musicking.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Minyumi on 2015. 12. 22..
 */
public class Music {
    private String album;
    private Integer albumId;
    private String albumImage;
    private Long id;
    private Integer month;
    private Integer rank;
    private Integer rankDate;
    private Long score;
    private String singer;
    private Integer singerId;
    private String singerImage;
    private Integer songId;
    private String songName;
    private String trimSinger;
    private String trimSong;
    private Integer year;

    public void parse(JSONObject object) throws JSONException {
        this.album = object.getString("album");
        this.albumId = object.getInt("albumId");
        this.albumImage  = object.getString("albumImage");
        this.id = object.getLong("id");
        this.month = object.getInt("month");
        this.rank = object.getInt("rank");
        this.rankDate = object.getInt("rankDate");
        this.score = object.getLong("score");
        this.singer = object.getString("singer");
        this.singerId = object.getInt("singerId");
        this.singerImage = object.getString("singerImage");
        this.songId = object.getInt("songId");
        this.songName = object.getString("songName");
        this.trimSinger = object.getString("trimSinger");
        this.trimSong = object.getString("trimSong");
        this.year = object.getInt("year");
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRankDate() {
        return rankDate;
    }

    public void setRankDate(Integer rankDate) {
        this.rankDate = rankDate;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getTrimSinger() {
        return trimSinger;
    }

    public void setTrimSinger(String trimSinger) {
        this.trimSinger = trimSinger;
    }

    public String getTrimSong() {
        return trimSong;
    }

    public void setTrimSong(String trimSong) {
        this.trimSong = trimSong;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Music{" +
                "year=" + year +
                ", trimSong='" + trimSong + '\'' +
                ", trimSinger='" + trimSinger + '\'' +
                ", songName='" + songName + '\'' +
                ", songId=" + songId +
                ", singerImage='" + singerImage + '\'' +
                ", singerId=" + singerId +
                ", singer='" + singer + '\'' +
                ", score=" + score +
                ", rankDate=" + rankDate +
                ", rank=" + rank +
                ", month=" + month +
                ", id=" + id +
                ", albumImage='" + albumImage + '\'' +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                '}';
    }
}
