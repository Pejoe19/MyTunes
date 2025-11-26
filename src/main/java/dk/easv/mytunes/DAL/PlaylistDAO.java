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
}
