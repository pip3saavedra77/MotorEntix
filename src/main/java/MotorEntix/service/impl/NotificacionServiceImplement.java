package MotorEntix.service.impl;

import MotorEntix.model.Notificacion;
import MotorEntix.repository.INotificacionRepository;
import MotorEntix.service.INotificacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServiceImplement implements INotificacionService {

    private final INotificacionRepository notificacionRepository;

    public NotificacionServiceImplement(INotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public Notificacion findById(Integer id) {
        return notificacionRepository.findById(id).orElse(null);
    }

    @Override
    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public void deleteById(Integer id) {
        notificacionRepository.deleteById(id);
    }
}
