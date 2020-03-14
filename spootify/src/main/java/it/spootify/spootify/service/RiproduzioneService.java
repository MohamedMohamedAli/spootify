package it.spootify.spootify.service;

import it.spootify.spootify.model.Riproduzione;
import it.spootify.spootify.web.dto.RiproduzioneProvaDTO;

public interface RiproduzioneService extends IBaseService<Riproduzione>{

	public Riproduzione avviaRiproduzione(RiproduzioneProvaDTO example);

}
