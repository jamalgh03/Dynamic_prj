package com.example.m_prj1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class Main extends Application {

    private String fontType = "Britannic Bold"; // font type
    private String fontSize = "16"; // font size
    private static int NumberOfCity = 0;
    private static String Start;
    private static String End;
    private static Petrol DbTable[][];
    private static City city[];
    private static int Petrolcost[][];
    static int CityCount = 0;
    static int StageCount = 0;


    @Override
    public void start(Stage stage) throws IOException {
        //=====================================Java Fx Parts =========================================================

        BorderPane root = new BorderPane(); // Border pane
        Scene scene = new Scene(root, 1250, 670); // scene
        root.setStyle("-fx-background-color: #708090;"); // scene color

        Label title = new Label("Traveling From City"); // title label
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");
        BorderPane.setAlignment(title, Pos.CENTER); // set Alignment by title => center
        root.setTop(title); // title => top

        TextField findtxt = new TextField(); // result text


        Label startLabel = new Label("Finf The Min Cost :"); // start label
        startLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");
        //style statr label
        Label endLabel = new Label("End"); // end label
        endLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");
        // style end label

        findtxt.setPromptText("The Minimum Cost is"); // result prompt text
        findtxt.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");
        findtxt.setPrefWidth(200); // width
        findtxt.setEffect(new DropShadow()); // effect

        Button findbtn = new Button("Find The Minimum Cost"); // btn result
        findbtn.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");
        findbtn.setEffect(new DropShadow());// effect


        TextField bstpath = new TextField(); // best path text field
        bstpath.setPromptText("The Best Path IS :"); // prompt
        bstpath.setStyle("-fx-background-color: #FFFFFF; -fx-font-family: '" + fontType + "'; -fx-font-size: "
                + 14 + "px;"); // table effect
        bstpath.setEffect(new DropShadow()); // table effect
        bstpath.setPrefWidth(200); // table width

        Button bstbtn = new Button("Best Path"); //Button best path
        Label lbpath = new Label("Best Path"); // label best path

        lbpath.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");
        bstbtn.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");

        bstbtn.setEffect(new DropShadow());// effect

        GridPane grd = new GridPane(); // Grid pane instead of HBox for top elements
        grd.setAlignment(Pos.CENTER_LEFT); // center left
        grd.setPadding(new Insets(20, 20, 20, 20)); // padding
        grd.setHgap(15); // Horizontal gap
        grd.setVgap(40); // Vertical gap

        grd.add(startLabel, 0, 0);
        grd.add(findtxt, 1, 0);
        grd.add(findbtn, 2, 0);
        grd.add(bstpath, 3, 0);
        grd.add(bstbtn, 4, 0);

        root.setLeft(grd); // GridPane set to the left


        Label ALt = new Label("The Alternative Path IS :");
        ALt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Cooper Black';");

        Button btnalt = new Button("Alternative Path");
        grd.add(ALt, 0, 1);
        grd.add(btnalt, 1, 1);

        btnalt.setStyle("-fx-background-color: #FFFFFF; -fx-font-family: '" + fontType + "'; -fx-font-size: "
                + fontSize + "px;"); // table effect
        btnalt.setEffect(new DropShadow()); // table effect


        TextArea alttxt = new TextArea();
        alttxt.setPrefWidth(70);
        alttxt.setPrefHeight(120);


        grd.add(alttxt, 0, 2, 5, 1);

        HBox twobtn = new HBox(20); //
        twobtn.setAlignment(Pos.CENTER);
        twobtn.setPadding(new Insets(20)); //


        Button goToTableButton = new Button("Go To Table");
        goToTableButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-family: '" + fontType + "'; -fx-font-size: "
                + 20 + "px;");
        goToTableButton.setEffect(new DropShadow()); // effect

        twobtn.getChildren().addAll(goToTableButton);

        root.setBottom(twobtn);

        BorderPane tblpane = new BorderPane();
        Scene tblscene = new Scene(tblpane, 1250, 670);
        tblpane.setStyle("-fx-background-color: #708090;"); // scene color


        TextArea tbtxt = new TextArea();
        tbtxt.setPrefSize(400, 500);

        Button bk = new Button("Go Back");
        bk.setStyle("-fx-background-color: #FFFFFF; -fx-font-family: '" + fontType + "'; -fx-font-size: "
                + 16 + "px;");
        bk.setPadding(new Insets(10, 10, 10, 10));

        Button tb = new Button("Show Table");
        tb.setStyle("-fx-background-color: #FFFFFF; -fx-font-family: '" + fontType + "'; -fx-font-size: "
                + 16 + "px;");
        tb.setPadding(new Insets(10, 10, 10, 10));

        VBox tbvbox = new VBox();
        tbvbox.getChildren().add(tbtxt);
        tbvbox.setAlignment(Pos.CENTER);

        HBox bthbox = new HBox();
        bthbox.getChildren().addAll(bk, tb);
        bthbox.setAlignment(Pos.CENTER);
        bthbox.setSpacing(20);
        bthbox.setPadding(new Insets(10, 10, 10, 10));

        //=====================================End Java Fx Parts =========================================================

        //=====================================Read File Parts =========================================================
        File file = new File("C:\\Users\\cts\\Desktop\\proj1.txt");
        Scanner sc;
        try {
            sc = new Scanner(file);
            NumberOfCity = sc.nextInt();// 14
            city = new City[NumberOfCity];// initialize City array
            DbTable = new Petrol[NumberOfCity][NumberOfCity];// initialize DB Table array
            Petrolcost = new int[NumberOfCity][NumberOfCity];// initialize petrol cost array
            sc.nextLine();
            String[] fromTo = sc.nextLine().split(", ");// Start, End
            Start = fromTo[0]; // Start
            End = fromTo[1];// End
            while (sc.hasNext()) {
                String line[] = sc.nextLine().split(", "); // split the line
                if (isExist(line[0]) == false) {
                    city[CityCount] = new City();// Create a new City object and assign it to the 'city' array at index
                    city[CityCount].setCityName(line[0]);
                    city[CityCount].setStage(StageCount);
                    city[CityCount].setHotelCost(0);
                    CityCount++;

                }
                stage(line);
                for (int i = 1; i < line.length; i++) {
                    String nextCity[] = line[i].replace("[", "").replace("]", "").split(",");
                    if (isExist(nextCity[0]) == false) {
                        city[CityCount] = new City();// Create a new City object and assign it to the 'city' array at index
                        city[CityCount].setCityName(nextCity[0]);
                        city[CityCount].setStage(StageCount);
                        city[CityCount].setHotelCost(Integer.parseInt(nextCity[2]));
                        CityCount++;
                    }
                    Petrolcost[findIndex(line[0])][findIndex(nextCity[0])] = Integer.parseInt(nextCity[1]);
                }

            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




        //=====================================End File Parts =========================================================

        //=====================================DP Table Parts =========================================================

//        If (i == j)
//        m[i][j] = 0
//
//        If (i < j)
//        m[i][j] = min { m[i][k] + m[k+1][j] + Petrolcost[i-1][k][j] }
//
//        If (i > j) no path
//

        if (findIndex(Start) > findIndex(End)
                || city[findIndex(Start)].getStage() == city[findIndex(End)].getStage()) {
            findtxt.setText("The City Is In Correct");
            alttxt.setText("The City Is In Correct");
            bstpath.setText("The City Is In Correct");
        }

            for (int i = findIndex(Start); i <= findIndex(End); i++) { // ال i بتمثل البداية
            for (int j = findIndex(Start); j <= findIndex(End); j++) { // ال j بتمثل النهاية
                DbTable[i][j] = new Petrol(); //  بنشئ obj جديد من  Petrol  لتمثيل cost path.


                if (i >= j || city[i].getStage() == city[j].getStage()) { // اذا رجعت رجوع او ضليت بنفس الستيج ما بكون عندي باث
                    DbTable[i][j].setCost(0);
                    DbTable[i][j].setPath("NO PATH");
                } else if (i < j) {
                    // اذا كانت ال End ب Stage بعد ال start
                    if (city[j].getStage() - city[i].getStage() == 1) {
                        // بحسب ال Cost لل hotel Cosr + petrol Cost لل مدينة النهائية
                        DbTable[i][j].setCost(Petrolcost[i][j] + city[j].getHotelCost());
                        // بطلع الباث من ال Start لل end
                        DbTable[i][j].setPath(city[i].getCityName() + " -> " + city[j].getCityName());

                    }
                }
            }
        }

        for (int i = findIndex(Start); i <= findIndex(End); i++) { // فور لوب للمدن من الستارت لل ايند ( ال i بتمثل الستارت)
            for (int j = findIndex(Start); j <= findIndex(End); j++) { // فور لوب للمدن من الستارت لل ايند ( ال j بتمثل الايند)
                if (i < j) {
                    if (city[j].getStage() - city[i].getStage() > 1) { // إذا كان الفرق بين الستيجز أكبر من 1
                        int index = findIndexInPreStage((city[j].getStage()) - 1); // البحث عن المدينة الأولى في الستيجز السابقة لستيج المدينة النهائية
                        for (int k = index; k < sizeOfStage((city[j].getStage()) - 1) + index; k++) { //
                            int cost = DbTable[k][j].getCost() + DbTable[i][k].getCost(); // حساب الCost للباث عبر ال ستيجز الوسيط
                            String path = DbTable[i][k].getPath()
                                    + DbTable[k][j].getPath().substring(1, DbTable[k][j].getPath().length()); // بجمع الباث للي مر من السنيج الوسيط
                            if (DbTable[i][j].getCost() > cost) { // إذا كان الكوست للباث الحالي أكبر من الكوست المحسوب
                                DbTable[i][j].setCost(cost); //بحدث الكوست
                                DbTable[i][j].setPath(path); // بحدث الباث
                            }
                        }

                    }
                }
            }
            tblpane.setTop(tbvbox);
            tblpane.setBottom(bthbox);

            goToTableButton.setOnAction(e -> {
                stage.setScene(tblscene);

            });
            bk.setOnAction(e -> {
                stage.setScene(scene);

            });
            tb.setOnAction(e -> {
                tbtxt.appendText("          ");
                for (int k = findIndex(Start); k <= findIndex(End); k++) {
                    tbtxt.appendText(String.format("%-10s", (city[k]).getCityName()));
                }
                tbtxt.appendText("\n");
                for (int l = findIndex(Start); l <= findIndex(End); l++) {
                    tbtxt.appendText(String.format("%-10s", city[l].getCityName()));
                    for (int j = findIndex(Start); j <= findIndex(End); j++) {
                        if (DbTable[l][j].getCost() == 0 && DbTable[l][l] != DbTable[j][j]) {
                            tbtxt.appendText(String.format("%-10s", ""));
                        } else
                            tbtxt.appendText(String.format("%-10s", DbTable[l][j].getCost()));
                    }
                    tbtxt.appendText("\n\n");
                }

                tbtxt.setStyle("-fx-font-family: 'Courier New', monospaced;-fx-font-weight:bold;-fx-font-size:14;");
            });


            //=====================================DP Table End =========================================================

            //=====================================Action Parts=========================================================

            findbtn.setOnAction(e -> {
                if (findIndex(Start) > findIndex(End)
                        || city[findIndex(Start)].getStage() == city[findIndex(End)].getStage()) {
                    findtxt.setText("The City Is In Correct");
                }
                findtxt.setText("The City Is In Correct");
                findtxt.setText(DbTable[findIndex(Start)][findIndex(End)].getCost() + "");

            });


            btnalt.setOnAction(e -> {
                if (findIndex(Start) > findIndex(End)
                        || city[findIndex(Start)].getStage() == city[findIndex(End)].getStage()) {
                    alttxt.setText("The City Is In Correct");
                }
                StringBuilder output = new StringBuilder();
                int[] count = {0};
                Alternativepath(findIndex(Start), new boolean[NumberOfCity], new int[NumberOfCity], 0, 0, count,
                        output);
                alttxt.setText(output.toString());
            });

            bstbtn.setOnAction(e -> {
                if (findIndex(Start) > findIndex(End)
                        || city[findIndex(Start)].getStage() == city[findIndex(End)].getStage()) {
                    bstpath.setText("The City Is In Correct");
                }
                bstpath.setText(DbTable[findIndex(Start)][findIndex(End)].getPath());
            });
        }
        //=====================================End Action=========================================================

        stage.setTitle("Traveling From City");
        stage.setScene(scene);
        stage.show();

    }
    public static void Alternativepath(int currentCityIndex, boolean[] visited, int[] path, int pathIndex, int totalCost,
                                       int[] count, StringBuilder output) {
        visited[currentCityIndex] = true; // ببدا ابحث من المدينة الحالية وبتحقق اذا زرتها
        path[pathIndex++] = currentCityIndex; // بضيف المدينة على الباث

        if (city[currentCityIndex].getCityName().equals(End)) { // اذا وصل للمدينة النهائية بطبع الباث
            if (count[0] < 3) { // بطبع اقل 3 باثات
                for (int i = 0; i < pathIndex; i++) {
                    output.append(city[path[i]].getCityName()); // إضافة اسم المدينة إلى الباث
                    if (i < pathIndex - 1) {
                        output.append(" -> "); // اذا كانت المدينة مش المدينة الاخيرة بضيف ->
                    }
                }
                output.append("\t \t").append(totalCost).append("\n"); // بضيف تكلفة الباث على الناتج
            }
            count[0]++; // زيادة عدد الباثات

        } else { // اذا المدينة الحالية هي مش المدينة النهائية
            for (int i = findIndex(Start); i <= findIndex(End); i++) {
                if (!visited[i] && DbTable[currentCityIndex][i].getCost() > 0) {// ببحث عن مدينة مجاورة
                    if (Math.abs(city[i].getStage() - city[currentCityIndex].getStage()) == 1) { // بفجص اذا في فارق ستيج بين المدينتين
                        Alternativepath(i, visited, path, pathIndex, totalCost + DbTable[currentCityIndex][i].getCost(),
                                count, output); // بشوق المسار المتاح
                    }
                }
            }
        }

        visited[currentCityIndex] = false;
    }

    public static int findIndexInPreStage(int stage) {
        for (int i = 0; i < city.length; i++) {
            if (city[i].getStage() == stage) {
                return i;
            }
        }
        return 0;
    }

    public static int sizeOfStage(int stage) {
        int count = 0;
        for (int i = 0; i < city.length; i++) {
            if (city[i].getStage() == stage) {
                count++;
            }
        }
        return count;
    }

    private static boolean isExist(String node) {
        for (int i = 0; i < city.length; i++) {
            if (city[i] != null) {// at the first, city is null
                if (city[i].getCityName().equals(node)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void stage(String[] Cities) {
        for (int i = 1; i < Cities.length; i++) {
            if (isExist(Cities[i].replace("[", "").replace("]", "").split(",")[0]) == true) {
                return;
            }
        }
        StageCount++;
    }

    private static int findIndex(String node) {
        for (int i = 0; i < city.length; i++) {
            if (city[i] != null) {
                if (city[i].getCityName().equals(node))
                    return i;
            }

        }
        return -1;
    }


}