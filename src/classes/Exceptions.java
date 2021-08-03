
package classes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *Exceptions Class
 * @author Mariya.Trenkina
 */
public class Exceptions {
//pop up error messafe in log in page

    /**
     *pop up template method
     * @param title popup title 
     * @param text popup body text
     */
public void popUp(String title, String text) {
Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(title);
			alert.setHeaderText(text);
			alert.showAndWait();
}

    /**
     *confirmation popup template method
     * @param textInput confirmPopUp body text
     */
    public void confirmPopUp(String textInput) {
Alert alert = new Alert(Alert.AlertType.CONFIRMATION, textInput, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

}

    /**
     *custom popup interface
     */
    public interface customPopUp {

    /**
     *
     * @param title title
     * @param header header
     * @param body body
     */
    void show(String title, String header, String body);
   }
}
