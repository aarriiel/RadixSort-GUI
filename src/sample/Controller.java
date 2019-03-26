package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;

public class Controller {
    @FXML
    private TextField radixArray;
    @FXML
    private TextField p1;
    @FXML
    private TextField p2;
    @FXML
    private TextField sortedArray;
    @FXML
    private Button sortButton;
    @FXML
    private Button clear;
    @FXML
    private TableView radixView1;
    @FXML
    private TableView radixView2;
    @FXML
    private TableColumn radixViewBucket1;
    @FXML
    private TableColumn radixViewNumber1;
    @FXML
    private TableColumn radixViewBucket2;
    @FXML
    private TableColumn radixViewNumber2;
    private String array="";
    private  int output[];
    private  int count[];
    private  int buc[];
    public void initialize(){
        radixViewBucket1.setCellValueFactory(new PropertyValueFactory<Person,String>("bucket"));
        radixViewNumber1.setCellValueFactory(new PropertyValueFactory<Person,String>("number"));
        radixViewBucket2.setCellValueFactory(new PropertyValueFactory<Person,String>("bucket"));
        radixViewNumber2.setCellValueFactory(new PropertyValueFactory<Person,String>("number"));
        p1.setDisable(true);
        p2.setDisable(true);
        sortButton.setOnAction(event -> control(1));
        clear.setOnAction(event -> control(2));
    }
    public void control(int i){
        switch (i){
            case 1:
                array=radixArray.getText();
                String[] tokens=array.split(",");
                int[] radix=new int[tokens.length];
                for(int a=0;a<radix.length;a++){
                    radix[a]=Integer.parseInt(tokens[a]);
                }
                countSort(radix, radix.length, 1);
                int d=0;
                String out="";
                for (int c=0;c<buc.length;c++) {
                    out="";
                    while(buc[c]!=0) {
                        out+=String.valueOf(output[d]+",");
                        buc[c]--;
                        d++;
                    }
                    Person person = new Person(String.valueOf(c), String.valueOf(out));
                    radixView1.getItems().add(person);
                }
                d=0;
                out="";
                for(int a=0;a<radix.length;a++){
                    out+=String.valueOf(output[d]+",");
                    d++;
                }
                countSort(radix, radix.length, 10);
                d=0;
                p1.setText(out);
                for (int c=0;c<buc.length;c++) {
                    out="";
                    while(buc[c]!=0) {
                        out+=String.valueOf(output[d]+",");
                        buc[c]--;
                        d++;
                    }
                    Person person = new Person(String.valueOf(c*10), String.valueOf(out));
                    radixView2.getItems().add(person);
                }
                d=0;
                out="";
                for(int a=0;a<radix.length;a++){
                    out+=String.valueOf(output[d]+",");
                    d++;
                }
                p2.setText(out);
                sortedArray.setText(out);
                break;
            case 2:
                radixArray.setText("");
                p1.setText("");
                p2.setText("");
                radixView1.getItems().clear();
                radixView2.getItems().clear();
                break;
        }
    }
    void countSort(int arr[], int n, int exp)
    {
        output = new int[n]; // output array
        int b;
        count = new int[10];
        buc=new int[10];
        Arrays.fill(count,0);
        for (b = 0; b < n; b++) {
            count[(arr[b] / exp) % 10]++;
            buc[(arr[b] / exp) % 10]++;
        }
        for (b = 1; b < 10; b++)
            count[b] += count[b - 1];
        for (b = n - 1; b >= 0; b--) {
            output[count[ (arr[b]/exp)%10 ] - 1] = arr[b];
            count[ (arr[b]/exp)%10 ]--;
        }
        for (b = 0; b < n; b++)
            arr[b] = output[b];
    }
}
