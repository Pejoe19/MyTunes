package dk.easv.mytunes.BLL;

import dk.easv.mytunes.Be.Playlist;
import dk.easv.mytunes.DAL.PlaylistDAO;

import java.util.List;

public class Logic {
    private final PlaylistDAO playlistData = new PlaylistDAO();


    public List<Playlist> getPlaylists() throws MusicException {
        return playlistData.getPlaylists();
    }

}
