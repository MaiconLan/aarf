ALTER TABLE matricula.configuracao_viagem DROP COLUMN dia_semana;
ALTER TABLE matricula.configuracao_viagem DROP COLUMN sentido;

ALTER TABLE matricula.viagem ADD COLUMN valor NUMERIC;
