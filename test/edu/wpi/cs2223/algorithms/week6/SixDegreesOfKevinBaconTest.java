package edu.wpi.cs2223.algorithms.week6;

import org.junit.jupiter.api.Test;
import edu.wpi.cs2223.algorithms.week6.cllam.BreadthFirstPaths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SixDegreesOfKevinBaconTest {

    @Test
    public void sutherlandAndDames(){
        PathInfo pathInfo = buildPathInfo("Donald Sutherland", "Margo Dames");

        assertTrue(pathInfo.hasPath);
        assertEquals(7, pathInfo.pathLength);
        assertEquals("Donald Sutherland - Buffy the Vampire Slayer (1992) - Rutger Hauer - Keetje Tippel (1975) - Dora van der Groen - Antonia (1995) - Margo Dames - ", pathInfo.path);
    }

    @Test
    public void sutherlandAndLisarelli(){
        PathInfo pathInfo = buildPathInfo("Donald Sutherland", "Giampiero Lisarelli");

        assertTrue(pathInfo.hasPath);
        assertEquals(9, pathInfo.pathLength);
        assertEquals("Donald Sutherland - Ordinary People (1980) - Elizabeth McGovern - Once Upon a Time in America (1984) - Salvatore Billa - Excellent Cadavers (1999) - Ricky Memphis - Branco, Il (1994) - Giampiero Lisarelli - ", pathInfo.path);
    }

    @Test
    public void sutherlandAndFaburel(){
        PathInfo pathInfo = buildPathInfo("Donald Sutherland", "Jacques Faburel");

        assertFalse(pathInfo.hasPath);
    }

    @Test
    public void portmanAndYamauchi(){
        PathInfo pathInfo = buildPathInfo("Natalie Portman", "Akira Yamauchi");

        assertTrue(pathInfo.hasPath);
        assertEquals(9, pathInfo.pathLength);
        assertEquals("Natalie Portman - Everyone Says I Love You (1996) - Alan Alda - Mephisto Waltz, The (1971) - Bradford Dillman - Mastermind (1976) - Wataru Omae - Gojira tai Hedora (1971) - Akira Yamauchi - ", pathInfo.path);

    }


    /**
     * Build a symbol graph from the included movies file.
     */
    SymbolGraph buildMovieGraph(){
        BufferedReader reader;

        int moviesCount = 1;

        Set<String> actors = new HashSet<>();
        Set<String> movies = new HashSet<>();
        Map<String, Set<String>> movieToActors = new HashMap<>();

        try {
            reader = new BufferedReader(new FileReader("resources/movies.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] parts = line.split("/");

                String moviePart = parts[0];
                String[] movieParts = moviePart.split("\\(");

                Set<String> actorsInMovie = new HashSet<>();

                String movieTitle = moviePart;

                for (int a = 0; a < parts.length; a++) {
                    String[] actorParts = parts[a].strip().split(",");

                    String actorFullName;

                    if (actorParts.length == 2) {
                        String actorLastName = actorParts[0].strip();
                        String actorFirstName = actorParts[1].strip();
                        actorFullName = actorFirstName + " " + actorLastName;
                    } else {
                        actorFullName = parts[a];
                    }

                    actors.add(actorFullName);
                    actorsInMovie.add(actorFullName);
                }


                // read next line
                line = reader.readLine();
                moviesCount++;

                movieToActors.put(movieTitle, actorsInMovie);

                if (movies.contains(movieTitle)){
                    System.out.println(movieTitle);
                }

                movies.add(movieTitle);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SymbolGraph symbolGraph = new SymbolGraph(movies.size() + actors.size());
        for (Map.Entry<String, Set<String>> entry: movieToActors.entrySet()){
            String movie = entry.getKey();
            Set<String> entryActors = entry.getValue();

            for (String entryActor: entryActors) {
                symbolGraph.addEdge(movie, entryActor);
            }
        }

        return symbolGraph;
    }

    /**
     * Simple container for info about the path from one actor to another.
     */
    class PathInfo{
        final boolean hasPath;
        final int pathLength;
        final String path;

        public PathInfo(boolean hasPath, int pathLength, String path) {
            this.hasPath = hasPath;
            this.pathLength = pathLength;
            this.path = path;
        }
    }

    /**
     * @return the path info from FromActor to toActor
     */
    PathInfo buildPathInfo(String fromActor, String toActor){
        SymbolGraph symbolGraph = buildMovieGraph();
        BreadthFirstPaths paths = new BreadthFirstPaths(symbolGraph.graph(), symbolGraph.convertNameToIndex(fromActor));

        boolean hasPath = paths.hasPathTo(symbolGraph.convertNameToIndex(toActor));
        if (!hasPath) {
            return new PathInfo(false, 0, null);
        }

        Iterable<Integer> path = paths.pathTo(symbolGraph.convertNameToIndex(toActor));

        String pathString = "";
        int length = 0;

        Iterable<Integer> desiredPath = paths.pathTo(symbolGraph.convertNameToIndex(toActor));
        for (Integer intNode : desiredPath) {
            String namedNode = symbolGraph.convertIndexToName(intNode);
            pathString = pathString + namedNode + " - ";
            length++;
        }

        return new PathInfo(true, length, pathString);
    }
}