package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BLL.MusicException;
import dk.easv.mytunes.Be.Playlist;

import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO {

    private final DBConnector dbConnector;

    {
        try {
            dbConnector = new DBConnector();
        } catch (MusicException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Playlist> getPlaylists() throws MusicException {
        ArrayList<Playlist> playlists = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM dbo.Playlists";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object
                int id = rs.getInt("Id");
                String name = rs.getString("Name");

                Playlist playlist = new Playlist(id, name);
                playlists.add(playlist);
            }
            return playlists;
        }
        catch (SQLException ex)
        {
            throw new MusicException("Could not get songs from database", ex);
        }
    }

    public void deletePlayList(Playlist playlist) throws Exception {
        String SQL = "delete from dbo.Playlists where id=?";
        try (Connection conn = DBConnector.getStaticConnection()){
            PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,playlist.getId());
            stmt.executeQuery();
        }
        catch (Exception e){
            throw new Exception("something went wrong",e);
        }
    }

    public Playlist updatePlaylist(Playlist playlist) throws MusicException {
        String sql = "UPDATE dbo.Playlists SET Name = ? WHERE Id = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, playlist.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new MusicException("Failed to update playlist, no rows affected");
            }
            return playlist;
        } catch (SQLException e) {
            throw new MusicException("Could not update playlist in database", e);
        }
    }
}
