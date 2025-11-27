package dk.easv.mytunes.BLL;

import dk.easv.mytunes.Be.Playlist;
import dk.easv.mytunes.Be.Song;
import dk.easv.mytunes.DAL.PlaylistDAO;
import dk.easv.mytunes.DAL.SongDAO;

import java.util.List;

public class Logic {

    private final SongDAO songData = new SongDAO();

    public Logic() throws MusicException {
    }

    public List<Song> getSongs() throws MusicException {
        return songData.getAllSongs();
    }

    private final PlaylistDAO playlistData = new PlaylistDAO();

    public List<Playlist> getPlaylists() throws MusicException {
        return playlistData.getPlaylists();
    }

    public void deletePlaylist(Playlist playlist) throws Exception {
        playlistData.deletePlayList(playlist);
    }

    public Song updateSong(Song song) throws MusicException {
        return songData.updateSong(song);
    }

    public void deleteSong(Song song) throws MusicException {
        songData.deleteSong(song);
    }
}
