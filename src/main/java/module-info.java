module com.example.m_prj1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.m_prj1 to javafx.fxml;
    exports com.example.m_prj1;
}