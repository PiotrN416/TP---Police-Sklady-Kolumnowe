package com.dq.police.controller.actions;


import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.helpers.IdsService;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.model.entities.Related;
import com.dq.police.model.entities.Task;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePresentationDataAction extends Action {

    private PersistenceManager persistence;

    @Override
    public String execute() {

        persistence.create(new Cop( "Adam", "Adamski", "Posterunkowy", "Glock 9mm"));
        persistence.create(new Cop( "Bartek", "Bartkowicz", "Patrol pieszy", "Magnum 40"));
        persistence.create(new Cop( "Filip", "Filipijczyk", "Patrol zmotoryzowany", "RPG-7"));

        persistence.create(new Related( "Grzegorz", "Grzesiak", "Swiadek morderstwa"));
        persistence.create(new Related( "Mateusz", "Mateusiak", "2 Lata w zawieszeniu"));
        persistence.create(new Related( "Tadeusz", "Tadeusiak", "Siedzi"));
        persistence.create(new Related( "Rafal", "Rafalski", "Poszukiwany"));

        persistence.create(new CrimePast( "Rafał Rafalski", "Kradzierz"));
        persistence.create(new CrimePast( "Rafał Rafalski", "Napad"));
        persistence.create(new CrimePast( "Mateusz Mateusiak", "Prowadzenie pod wpływem alkoholu"));
        persistence.create(new CrimePast( "Tadeusz Tadeusiak", "Handel narkotykami"));

        persistence.create(new Task("Zlapac Rafala Falalskiego","Poszukiwanie","Dosc pilne","Filip Filipijczyk", "W trakcie"));
        persistence.create(new Task("Odszukac zagubiony radiowoz","Poszukiwanie","Dosc pilne","Bartek Bartkowicz", "W trakcie"));
        persistence.create(new Task("Odebrac dostawe paczkow","Administracja","Pilne","Adam Adamski", "Zakonczone"));

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Create Presentation Data";
    }
}
