package com.jpaLoadFromXml.intf;

import com.jpaLoadFromXml.entities.ContactEntity;

import java.util.List;

public interface ContactService {

    // Найти все контакты.
    public List<ContactEntity> findAll();
    // Найти все контакты с заданным телефоном и хобби.
    public List<ContactEntity> findAllWithDetail();
    // Найти контакт со всеми деталями по идентификатору.
    public ContactEntity findById(Integer id);
    // Вставить или обновить контакт.
    public ContactEntity save(ContactEntity contact);
    // Удалить контакт.
    public void delete(ContactEntity contact);
}
