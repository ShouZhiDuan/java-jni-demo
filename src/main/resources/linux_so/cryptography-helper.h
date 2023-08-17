#include <cstdarg>
#include <cstdint>
#include <cstdlib>
#include <ostream>
#include <new>

constexpr static const uintptr_t BUFFER_LEN = 32;

extern "C" {

//Hash
void cal_digest(const uint8_t *data, uint32_t data_len, uint8_t *digest);

//秘钥
void ed25519_gen_priv_key(uint8_t *ptr);

//加密
int32_t ed25519_encrypt(const uint8_t *digest, const uint8_t *priv_key, uint8_t *data_enc);

//解密
int32_t ed25519_decrypt(const uint8_t *data, const uint8_t *priv_key, uint8_t *result);

//乘法运算
int32_t ed25519_mul(const uint8_t *data, const uint8_t *priv_key, uint8_t *result);

} // extern "C"
