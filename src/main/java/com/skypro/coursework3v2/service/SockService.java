package com.skypro.coursework3v2.service;

import com.skypro.coursework3v2.dto.SocksRequest;
import com.skypro.coursework3v2.exception.InsufficientSockQuantityException;
import com.skypro.coursework3v2.exception.InvalidSockException;
import com.skypro.coursework3v2.model.Colour;
import com.skypro.coursework3v2.model.Size;
import com.skypro.coursework3v2.model.Sock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SockService {
    private final Map<Sock, Integer> socks = new HashMap<>();


    public void addSocks(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Sock sock = mapToSock(socksRequest);
        if (socks.containsKey(sock)){
            socks.put(sock, socks.get(sock) + socksRequest.getQuantity());
        } else {
            socks.put(sock, socksRequest.getQuantity());
        }
    }

    public void issueSocks(SocksRequest socksRequest){
        decreaseSockQuantity(socksRequest);
    }

    public int getSockQuantity(Colour colour, Size size, Integer cottonMin, Integer cottonMax){
        int total = 0;
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()){
            if (colour != null && !entry.getKey().getColour().equals(colour)){
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)){
                continue;
            }
            if (cottonMin != null  && entry.getKey().getCotton() < cottonMin){
                continue;
            }
            if (cottonMax != null && entry.getKey().getCotton() > cottonMax){
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    private void validateRequest(SocksRequest socksRequest){
        if(socksRequest.getColour()==null || socksRequest.getSize() == null){
            throw new InvalidSockException("Заполните все поля!");
        }
        if(socksRequest.getQuantity() <= 0){
            throw new InvalidSockException("Количество должно быть больше 0");
        }
        if(socksRequest.getCotton() < 0 || socksRequest.getCotton() > 100){
            throw new InvalidSockException("Неверно указано процентное соотношение хлопка");
        }
    }

    private Sock mapToSock(SocksRequest socksRequest){
        return new Sock(socksRequest.getColour(),
                socksRequest.getSize(),
                socksRequest.getCotton());
    }

    public void removeSocks(SocksRequest socksRequest) {
        decreaseSockQuantity(socksRequest);
    }

    private void decreaseSockQuantity(SocksRequest socksRequest){
        validateRequest(socksRequest);
        Sock sock = mapToSock(socksRequest);
        int socksQuantity = socks.getOrDefault(sock, 0);
        if (socksQuantity >= socksRequest.getQuantity()){
            socks.put(sock, socksQuantity - socksRequest.getQuantity());
        } else {
            throw new InsufficientSockQuantityException("Носки не обнаружены");
        }
    }
}
