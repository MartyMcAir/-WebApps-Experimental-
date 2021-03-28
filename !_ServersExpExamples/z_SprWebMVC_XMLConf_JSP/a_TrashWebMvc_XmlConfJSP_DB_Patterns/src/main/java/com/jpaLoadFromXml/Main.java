package com.jpaLoadFromXml;

import com.jpaLoadFromXml.entities.ContactEntity;
import com.jpaLoadFromXml.entities.ContactTelDetailEntity;
import com.jpaLoadFromXml.entities.HobbyEntity;
import com.jpaLoadFromXml.impl.ContactSummaryUntypeImpl;
import com.jpaLoadFromXml.intf.ContactService;
import com.jpaLoadFromXml.intf.ContactSummaryService;
import com.jpaLoadFromXml.supportClasses.ContactSummary;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

// JAVA Study
public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:jpa/spring-config.xml");
        ctx.refresh();

        /* insert delete update tutorial. Пример вставки обновления удаления */
        insertUpdateExample(ctx);
        deleteExample(ctx);

        /* untype select tutorial. Пример нетипизированных запросов */
//        untypeTutorial(ctx);
        /* from Hello World article. Из статьи JPA - пример Hello World */
//        helloWorldTutorial(ctx);
        /* insert delete update tutorial. Пример вставки обновления удаления */

    }

    private static void insertUpdateExample(GenericXmlApplicationContext ctx) {
        ContactService service = ctx.getBean("jpaContactService", ContactService.class);

        ContactEntity contact = new ContactEntity();
        contact.addContactTelDetail(new ContactTelDetailEntity("Городской", "8-499-000-333"));
        contact.setFirstName("NameInsert");
        contact.setLastName("LastNameInsert");
        contact.setBirthDate(new java.util.Date());

        ContactTelDetailEntity contactTelDetail = new ContactTelDetailEntity("Home", "1111111111");
        contact.addContactTelDetail(contactTelDetail);

        contactTelDetail = new ContactTelDetailEntity("Городской", "8-499-000-333");
        contact.addContactTelDetail(contactTelDetail);

        contactTelDetail = new ContactTelDetailEntity("Mobile", "2222222222");
        contact.addContactTelDetail(contactTelDetail);

        service.save(contact);

        /*update example */
        ContactEntity existContact = service.findById(contact.getId()); //contact.id - insert before
        existContact.setLastName("updatedLastName");
        existContact.setFirstName("updatedNewName");
        ContactTelDetailEntity toDeleteTel = null;
        for (ContactTelDetailEntity c : existContact.getContactTelDetails()) {
            if (c.getTelType().equals("Mobile")) {
                toDeleteTel = c;
            }
        }
        existContact.removeContactTelDetail(toDeleteTel);
        service.save(existContact);
    }

    private static void deleteExample(GenericXmlApplicationContext ctx) {
        ContactService service = ctx.getBean("jpaContactService", ContactService.class);
        ContactEntity contactEntity = service.findById(28);
        service.delete(contactEntity);
    }

    private static void untypeTutorial(GenericXmlApplicationContext ctx) {
        ContactSummaryUntypeImpl summaryUntype = ctx.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
        summaryUntype.displayAllContactSummary();

        /*select with consturctor */
        ContactSummaryService contactSummaryService = ctx.getBean("contactSummaryService", ContactSummaryService.class);
        List<ContactSummary> contactSummaryList = contactSummaryService.findAllSummary();
        for (ContactSummary cs : contactSummaryList) {
            System.out.println(cs);
        }
    }

    private static void helloWorldTutorial(GenericXmlApplicationContext ctx) {
        ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);
        List<ContactEntity> contacts = contactService.findAll();

        System.out.println("JPA tutorial. Contact");
        for (ContactEntity contact : contacts) {
            System.out.println(contact);
        }

        List<ContactEntity> cotactsWithDetail = contactService.findAllWithDetail();
        testContactDetail(cotactsWithDetail);

        ContactEntity contactById = contactService.findById(8);
        System.out.println(contactById);
    }

    private static void testContactDetail(List<ContactEntity> contacts) {
        System.out.println("Contact with detail");
        for (ContactEntity contact : contacts) {
            System.out.println("Contact: ");
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetailEntity tel : contact.getContactTelDetails()) {
                    System.out.println("Details: ");
                    System.out.println(tel);
                }
            }

            System.out.println("Hobby: ");
            if (contact.getHobbies() != null) {
                for (HobbyEntity hobby : contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }
        }
    }

}