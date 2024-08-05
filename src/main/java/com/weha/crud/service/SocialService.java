package com.weha.crud.service;

import com.weha.crud.dtos.SocialDTO;
import com.weha.crud.entity.SocialEntity;
import com.weha.crud.entity.UserEntity;
import com.weha.crud.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository socialRepository;

    public SocialService(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    public SocialDTO findByUser(UserEntity user) {
        return socialRepository.findByUser(user)
                .map(s -> new SocialDTO(
                        s.getFacebook(),
                        s.getLine(),
                        s.getInstagram(),
                        s.getTiktok()
                )).orElse(null);
    }

    public void createSocial(UserEntity user, SocialDTO req) {
        SocialEntity entity = new SocialEntity();
        entity.setFacebook(req.facebook());
        entity.setLine(req.line());
        entity.setTiktok(req.tiktok());
        entity.setInstagram(req.instagram());
        entity.setUser(user);
        socialRepository.save(entity);
    }

    public void updateSocial(long id, UserEntity user, SocialDTO req) {
        Optional<SocialEntity> social = socialRepository.findById(id);
        if (social.isPresent()) {
            SocialEntity entity = social.get();
            entity.setFacebook(req.facebook());
            entity.setLine(req.line());
            entity.setInstagram(req.instagram());
            entity.setTiktok(req.tiktok());
            entity.setModifierDate(LocalDateTime.now());
            socialRepository.save(entity);
        }
    }
}
