package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// From CPSC-210 given persistence example code:
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
        String currentTurn = jsonObject.getString("currentTurn");
        Game g = new Game();
        loadBoard(g, jsonObject);
        loadCapturedPieces(g, jsonObject);
        if (currentTurn.equals("black")) {
            g.flipTurn();
        }
        return g;
    }

    private void loadCapturedPieces(Game g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("capturedPieces");
        for (Object json : jsonArray) {
            JSONObject piece = (JSONObject) json;
            g.getCapturedPieces().add(loadPiece(piece));
        }
    }

    // MODIFIES: g
    // EFFECTS: parses Board from JSON object and adds it to Game
    private void loadBoard(Game g, JSONObject jsonObject) {
        loadSquares(g, jsonObject);
    }

    // MODIFIES: g
    // EFFECTS: parses squares from JSON object and adds them to Board's squareList
    private void loadSquares(Game g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONObject("board").getJSONArray("squareList");
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
            square.setPiece(loadPiece(jsonObject.getJSONObject("piece")));
        }
        g.getBoard().replaceSquare(square);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private BasePiece loadPiece(JSONObject jsonObject) {
        String pieceName = jsonObject.getString("pieceName");
        String colour = jsonObject.getString("colour");
        BasePiece piece = null;
        if (pieceName.equals("Bishop")) {
            piece = new Bishop(colour);
        } else if (pieceName.equals("King")) {
            piece = new King(colour);
        } else if (pieceName.equals("Dragon")) {
            piece = new Dragon(colour);
        } else if (pieceName.equals("Princess")) {
            piece = new Princess(colour);
        } else if (pieceName.equals("Rook")) {
            piece = new Rook(colour);
        } else if (pieceName.equals("Bishop")) {
            piece = new Bishop(colour);
        } else if (pieceName.equals("Pawn")) {
            piece = new Pawn(colour);
        } else if (pieceName.equals("Knight")) {
            piece = new Knight(colour);
        } else if (pieceName.equals("Queen")) {
            piece = new Queen(colour);
        }
        return piece;
    }
}