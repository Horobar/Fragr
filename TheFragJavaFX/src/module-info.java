module TheFragJavaFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires jdk.compiler;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
}
