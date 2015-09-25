import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

public class FileChooser extends Component {
	public String chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);

		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
