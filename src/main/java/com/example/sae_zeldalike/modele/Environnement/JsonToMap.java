package com.example.sae_zeldalike.modele.Environnement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToMap {

    public static int[][] loadMapFromJson(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject rootNode = new JSONObject(content);

        JSONArray layersArray = rootNode.getJSONArray("layers");
        if (layersArray.length() > 0) {
            JSONObject layerObject = layersArray.getJSONObject(0);
            JSONArray dataArray = layerObject.getJSONArray("data");
            int width = layerObject.getInt("width");
            int height = layerObject.getInt("height");

            if (dataArray.length() != width * height) {
                throw new IOException("Donnée invalide sur la map: length ne concorde pas avec width * height");
            }

            int[][] map = new int[height][width];
            int dataIndex = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    map[y][x] = dataArray.getInt(dataIndex++);
                }
            }
            return map;
        } else {
            throw new IOException("Format de map invalide : no layers found");
        }
    }
}
