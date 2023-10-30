package org.example.Robot;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/*
    @zorroperro_bot
* 5613780651:AAHqGVu8u3ZFtOuxllA7Cm-7Jk_cfpCNuMw
* */
public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername(){
        return "@zorroperro_bot";
    }

    @Override
    public String getBotToken(){
        return "5613780651:AAHqGVu8u3ZFtOuxllA7Cm-7Jk_cfpCNuMw";
    }

    private SendMessage generateSendMessage(Long chatId, int characterCount){
        System.out.println(chatId);

        Long numero2 = 177279577L; //id de usuario de chat en gram Dan

        int comparacion = chatId.compareTo(numero2);
        if(comparacion == 0){
            return new SendMessage(chatId.toString(), "respuesta: "+chatId);
        }else{
            return new SendMessage(chatId.toString(), " "+characterCount + " / caracteres");
        }
    }
    private SendMessage envioMensajeDinamico(Long chatId){
        if(chatId.equals(177279577L)){
            sendMessage(envioMensajeNombre(chatId));
        }else{
            return new SendMessage(chatId.toString(), "Hola esto es un texto personalizado para ti");
        }
        return null;
    }
    private SendMessage envioMensajeNombre(Long chatId){
        return new SendMessage(chatId.toString(), "Daniel tu usuario es: "+chatId);
    }


    private void sendMessage(SendMessage sendMessage){
        try{
            execute(sendMessage);
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        int length = message.length();
        //condicion
        if(message.equals("Hola") || message.equals("hola")){
            System.out.println("ingreso un hola... del usuario: "+ chatId);
            sendMessage(envioMensajeDinamico(chatId));
        }else{
            System.out.println("Mensaje: "+message);
            System.out.println("El mensaje "+length+" caracteres");
            sendMessage(generateSendMessage(chatId, length));
        }
    }
}
