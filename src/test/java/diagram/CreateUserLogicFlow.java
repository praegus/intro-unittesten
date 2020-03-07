package diagram;

import ch.ifocusit.plantuml.classdiagram.ClassDiagramBuilder;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.User;


public class CreateUserLogicFlow {


//    skinparam classFontName DejaVu Serif
//    skinparam defaultFontName monospaced
//    skinparam dpi 300


    public static void main(String... args) {
        System.out.println("------------------------");
        Package pDto = User.class.getPackage();
        String dtoDiagram = new ClassDiagramBuilder()
                .addClasse(vakantieplanner.dto.User.class)
                .addClasse(vakantieplanner.dto.Reservation.class)
                .addClasse(vakantieplanner.dto.ReserveRequest.class)
                .addClasse(vakantieplanner.dto.ReserveResponse.class)
                .build();
        System.out.println(dtoDiagram);
        System.out.println("#######################################");

        String diagram = new ClassDiagramBuilder()
//                .addClasse(vakantieplanner.dto.User.class)
//                .addClasse(vakantieplanner.dto.Reservation.class)
                //
//                .addClasse(vakantieplanner.dto.User.class)
                .addClasse(vakantieplanner.business.ReserveLogic.class)
                .addClasse(vakantieplanner.business.ReserveLogicImpl.class)
                .addClasse(vakantieplanner.database.DataAccessLayer.class)
                .addClasse(vakantieplanner.database.DataAccessLayerImpl.class)
                .addClasse(vakantieplanner.controller.UserController.class)
                .addClasse(vakantieplanner.business.UserLogic.class)
                .addClasse(vakantieplanner.business.UserLogicImpl.class)
                .build();
        System.out.println(diagram);
        System.out.println("------------------------");
    }
}
