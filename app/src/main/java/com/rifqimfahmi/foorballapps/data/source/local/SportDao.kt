package com.rifqimfahmi.foorballapps.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rifqimfahmi.foorballapps.data.source.local.subset.FavoriteCount
import com.rifqimfahmi.foorballapps.features.matches.MatchesListFragment
import com.rifqimfahmi.foorballapps.testing.OpenForTesting
import com.rifqimfahmi.foorballapps.vo.*

/*
 * Created by Rifqi Mulya Fahmi on 21/11/18.
 */

@Dao
@OpenForTesting
interface SportDao {

    @Query("SELECT * FROM matches WHERE idLeague = :idLeague AND matchType = '${MatchesListFragment.TYPE_NEXT_MATCH}' ORDER BY dateEvent DESC LIMIT 15")
    fun getNextMatches(idLeague: String?): LiveData<List<Match>>

    @Query("SELECT * FROM matches WHERE idLeague = :idLeague AND matchType = '${MatchesListFragment.TYPE_PREV_MATCH}' ORDER BY dateEvent DESC LIMIT 15")
    fun getPrevMatches(idLeague: String?): LiveData<List<Match>>

    @Query("DELETE FROM matches WHERE matchType = '${MatchesListFragment.TYPE_NEXT_MATCH}' AND idLeague = :idLeague")
    fun deleteNextMatches(idLeague: String?)

    @Query("DELETE FROM matches WHERE matchType = '${MatchesListFragment.TYPE_PREV_MATCH}' AND idLeague = :idLeague")
    fun deletePrevMatches(idLeague: String?)

    @Query("SELECT * FROM teams WHERE idLeague = :leagueId")
    fun getTeams(leagueId: String): LiveData<List<Team>>

    @Query("SELECT * FROM teams WHERE idTeam = :teamId")
    fun getTeam(teamId: String): LiveData<Team>

    @Query("SELECT * FROM matches WHERE idEvent = :matchId")
    fun getMatchDetail(matchId: String): LiveData<Match>

    @Query("SELECT * FROM players WHERE idTeam = :teamId")
    fun getPlayers(teamId: String): LiveData<List<Player>>

    @Query("SELECT COUNT(*) as favCount FROM favorite_matches WHERE idMatch = :idEvent")
    fun isFavoriteMatch(idEvent: String): LiveData<FavoriteCount>

    @Query("SELECT COUNT(*) as favCount FROM favorite_teams WHERE idTeam = :teamId")
    fun isFavoriteTeam(teamId: String): LiveData<FavoriteCount>

    @Query("DELETE FROM favorite_matches WHERE idMatch = :matchId")
    fun deleteFavoriteMatch(matchId: String)

    @Query("DELETE FROM favorite_teams WHERE idTeam = :teamId")
    fun deleteFavoriteTeam(teamId: String)

    @Query("SELECT * FROM matches INNER JOIN favorite_matches ON favorite_matches.idMatch = idEvent ORDER BY dateEvent DESC")
    fun getFavoriteMatches(): LiveData<List<Match>>

    @Query("SELECT * FROM teams INNER JOIN favorite_teams ON favorite_teams.idTeam = teams.idTeam")
    fun getFavoriteTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM players WHERE idPlayer = :playerId")
    fun getPlayer(playerId: String): LiveData<Player>

    @Insert(onConflict = REPLACE)
    fun saveMatches(matches: List<Match?>)

    @Insert(onConflict = REPLACE)
    fun saveTeams(it: List<Team?>)

    @Insert(onConflict = REPLACE)
    fun savePlayers(players: List<Player?>)

    @Insert(onConflict = REPLACE)
    fun addToFavoriteMatch(favMatch: FavoriteMatch)

    @Insert(onConflict = REPLACE)
    fun addToFavoriteTeam(favoriteTeam: FavoriteTeam)

    @Query("SELECT * FROM matches WHERE strEvent LIKE :query ORDER BY dateEvent DESC")
    fun searchMatch(query: String): LiveData<List<Match>>

    @Query("SELECT * FROM teams WHERE strTeam LIKE :query")
    fun searchTeam(query: String): LiveData<List<Team>>


}