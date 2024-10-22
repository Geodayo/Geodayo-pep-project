package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    //Create a new message
    public Message createMessage(Message message) {

        if (message.getMessage_text() == "" || message.getMessage_text().length() > 255) {
            System.out.println("HIT blank or length");
            return null;
        }
        //check if there is an existing user
        if (!accountDAO.findUserById(message.getPosted_by())) {
            System.out.println("HIT EXISTING USER NULL");
            return null;
        }
        
        System.out.println("USER GETTING CREATED:  "+message);
        return messageDAO.createMessage(message);
    }

    //Return all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    //Return message by id
    public Message getMessageById(int message_id) {
        if (messageDAO.getMesageById(message_id) != null) {
            return messageDAO.getMesageById(message_id);
        } else {            
            return null;
        }
    }

    //Delete message by id
    public Message deleteMessageById(int messageId) {
        return messageDAO.deleteMessageById(messageId);
    }

    //Update Message by ID
    public Message updateMessageText(int messageId, String newMessageText) {
        if (newMessageText == null || newMessageText == "" || newMessageText.length() > 255) {
            return null;
        }
        return messageDAO.updateMessageText(messageId, newMessageText);
    }

    //Get Messages by User
    public List<Message> getMessagesByUser(int accountId) {
        return messageDAO.getMessagesByUser(accountId);
    }
}
