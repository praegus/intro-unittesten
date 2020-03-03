package diagram;

import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.database.DataAccessLayerImpl;
import vakantieplanner.dto.User;

import ch.ifocusit.plantuml.classdiagram.ClassDiagramBuilder;

public class CreatePackages {
    public static void main(String... args) {
        Package pDto = vakantieplanner.dto.User.class.getPackage();
        Package pAuth = vakantieplanner.auth.UserDetailsServiceImpl.class.getPackage();
        Package pBusiness = vakantieplanner.business.UserLogic.class.getPackage();
        Package pDatabase = vakantieplanner.database.DataAccessLayer.class.getPackage();
        Package pController = vakantieplanner.controller.UserController.class.getPackage();

        String diagram = new ClassDiagramBuilder()
                .addPackage(pDto, pBusiness, pDatabase, pController)
                .setHeader("Vakantieplanner")
                .build();
        System.out.println(diagram);
    }
}
