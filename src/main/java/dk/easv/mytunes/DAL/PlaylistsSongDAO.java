package dk.easv.mytunes.DAL;

import dk.easv.mytunes.Be.IndexSong;
import dk.easv.mytunes.Be.Playlist;
import dk.easv.mytunes.Be.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                Song placeholdersong = new Song(songId,null,null,null,0,null);
                IndexSong indexSong = new IndexSong(placeholdersong,index);
                indexSongArrayList.add(indexSong);
            }
            return indexSongArrayList;
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
