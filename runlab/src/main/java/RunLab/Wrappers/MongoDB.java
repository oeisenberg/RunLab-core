// package RunLab.Wrappers;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.util.Map;
// import java.util.function.Consumer;

// import com.google.gson.JsonParser;
// import com.google.gson.JsonObject;

// import com.mongodb.MongoClient;
// import com.mongodb.MongoClientURI;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import com.mongodb.client.MongoIterable;

// import RunLab.Utility.JsonConverter;

// public class MongoDB {

//     public MongoDB(){
//         MongoClientURI uri = new MongoClientURI(
//             "mongodb+srv://dbUser_ohe:"+getPwd()+"@runlab-agtdb.azure.mongodb.net/RunLab?retryWrites=true&w=majority");

//         MongoClient mongoClient = new MongoClient(uri);
//         mongoClient.listDatabaseNames().forEach((Consumer<String>) System.out::println);

//         mongoClient.close();
//     }

//     private String getPwd(){
//         File file = new File("C:\\Users\\olive\\Dropbox\\Programming\\RunLab\\backend\\runlab\\mongo.json");

//         try {
//             JsonObject object = (JsonObject) JsonParser.parseReader(new FileReader(file));
//             Map<String, Object> attributes = JsonConverter.toMap(object);

//             return JsonConverter.toString(attributes, "pwd");
//         } catch (FileNotFoundException fnfE) {
//             System.err.println("File read failed");
//             return "";
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

// }