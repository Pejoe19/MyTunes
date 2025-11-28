package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BLL.MusicException;
import dk.easv.mytunes.Be.IndexSong;
import dk.easv.mytunes.Be.Playlist;
import dk.easv.mytunes.Be.Song;

import java.sql.*;
import java.util.ArrayList;

public class PlaylistsSongDAO {

    public ArrayList<IndexSong> getPlaylistsSong(Playlist playlist) throws Exception {
        String sql = "select * from dbo.SongPlaylistRelation where playlistId = ?";
        ArrayList<IndexSong> indexSongArrayList = new ArrayList<>();
        try(Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,playlist.getId());
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int index = rs.getInt("index");
                int songId = rs.getInt("SongId");
                Song placeholdersong = new Song(songId, "", "", "", 0, "");
                IndexSong indexSong = new IndexSong(placeholdersong,index);
                indexSongArrayList.add(indexSong);
            }
            return indexSongArrayList;
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void removeSongFromPlaylist(Playlist playlist, Song song) throws Exception {
        String sql = "DELETE FROM dbo.SongPlaylistRelation WHERE PlaylistId = ? AND SongId = ?";

        try (Connection conn = DBConnector.getStaticConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playlist.getId());
            stmt.setInt(2, song.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Could not remove song from playlist", e);
        }
    }
}
