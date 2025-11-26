package dk.easv.mytunes.DAL;

import dk.easv.mytunes.Be.Song;
import dk.easv.mytunes.BLL.MusicException;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    private final DBConnector dbConnector;

    public SongDAO(){
        try {
            dbConnector = new DBConnector();
        } catch (MusicException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Song> getAllSongs() throws MusicException {
        List<Song> songs = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM Songs";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                LocalTime localTime = rs.getTime("Time").toLocalTime();
                int time = localTime.toSecondOfDay();
                String filePath = rs.getString("File");

                songs.add(new Song(id, title, artist, category, time, filePath));
            }

        } catch (Exception e) {
            throw new MusicException("Could not load songs from DB", e);
        }
        return songs;
    }
}