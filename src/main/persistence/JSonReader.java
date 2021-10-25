package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JSonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JSonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Game from JSON object and returns it
    private Game parseGame(JSONObject jsonObject) {
        Game g = new Game();
        loadBoard(g, jsonObject);
        return g;
    }

    // MODIFIES: g
    // EFFECTS: parses Board from JSON object and adds it to Game
    private void loadBoard(Game g, JSONObject jsonObject) {
        loadSquares(g, jsonObject);
    }

    // MODIFIES: g
    // EFFECTS: parses squares from JSON object and adds them to Board's squareList
    private void loadSquares(Game g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("squareList");
        for (Object json : jsonArray) {
            JSONObject square = (JSONObject) json;
            loadSingleSquare(g, square);
        }
    }

    private void loadSingleSquare(Game g, JSONObject jsonObject) {
        int posX = jsonObject.getInt("x");
        int posY = jsonObject.getInt("y");
        Square square = new Square(posX, posY, null);
        if (jsonObject.has("piece")) {
            square = loadPiece(square, jsonObject);
        }
        g.getBoard().replaceSquare(square);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private Square loadPiece(Square square, JSONObject jsonObject) {
        String colour;
        if (jsonObject.has("black")) {
            colour = "black";
        } else {
            colour = "white";
        }
        if (jsonObject.has("Bishop")) {
            square.setPiece(new Bishop(colour));
        } else if (jsonObject.has("King")) {
            square.setPiece(new King(colour));
        }  else if (jsonObject.has("Dragon")) {
            square.setPiece(new Dragon(colour));
        } else if (jsonObject.has("Knight")) {
            square.setPiece(new Knight(colour));
        } else if (jsonObject.has("Queen")) {
            square.setPiece(new Queen(colour));
        } else if (jsonObject.has("Rook")) {
            square.setPiece(new Rook(colour));
        } else if (jsonObject.has("Pawn")) {
            square.setPiece(new Pawn(colour));
        } else if (jsonObject.has("Princess")) {
            square.setPiece(new Princess(colour));
        }
        return square;
    }
}