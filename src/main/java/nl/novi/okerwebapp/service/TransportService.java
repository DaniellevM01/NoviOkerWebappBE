package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.model.Transport;
import nl.novi.okerwebapp.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    public Iterable<Transport> getTransports() {
        return transportRepository.findAll();
    }
}